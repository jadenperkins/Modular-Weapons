#version 400 core

in vec2 passTextureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 outColor;

uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColor;

void main(void) {
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

    vec4 textureColor = texture(textureSampler, passTextureCoords);
    if(textureColor.a < 0.5) {
        discard;
    }

    outColor = vec4(finalSpecular, 1.0) + vec4(diffuse, 1.0) * texture(textureSampler, passTextureCoords);
    outColor = mix(vec4(skyColor, 1.0), outColor, visibility);
}