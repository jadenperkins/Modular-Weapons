#version 400 core

in vec2 passTextureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 outColor;

uniform sampler2D backgroundTexture;
uniform sampler2D rTexture;
uniform sampler2D gTexture;
uniform sampler2D bTexture;
uniform sampler2D blendMap;

uniform vec3 lightColor;
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColor;

void main(void) {
    vec4 blendMapColor = texture(blendMap, passTextureCoords);

    float backTextureAmount = 1 - (blendMapColor.r + blendMapColor.g + blendMapColor.b);
    vec2 tileCoords = passTextureCoords * 40.0;

    vec4 backgroundTextureColor = texture(backgroundTexture, tileCoords) * backTextureAmount;
    vec4 rTextureAmount = texture(rTexture, tileCoords) * blendMapColor.r;
    vec4 gTextureAmount = texture(gTexture, tileCoords) * blendMapColor.g;
    vec4 bTextureAmount = texture(bTexture, tileCoords) * blendMapColor.b;

    vec4 totalColor = backgroundTextureColor + rTextureAmount + gTextureAmount + bTextureAmount;

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLight = normalize(toLightVector);

    float nDot1 = dot(unitNormal, unitLight);
    float brightness = max(nDot1, 0.2);
    vec3 diffuse = brightness * lightColor;

    vec3 unitCamera = normalize(toCameraVector);
    vec3 lightDirection = -unitLight;
    vec3 reflectedLight = reflect(lightDirection, unitNormal);

    float specularFactor = dot(reflectedLight, unitCamera);
    specularFactor = max(specularFactor, 0.0);
    float dampedFactor = pow(specularFactor, shineDamper);
    vec3 finalSpecular = dampedFactor * reflectivity * lightColor;

    outColor = vec4(diffuse, 1.0) * totalColor + vec4(finalSpecular, 1.0);
    outColor = mix(vec4(skyColor, 1.0), outColor, visibility);
}