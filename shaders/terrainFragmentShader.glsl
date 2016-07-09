#version 400 core

in vec2 passTextureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector[4];
in vec3 toCameraVector;
in float visibility;
in vec4 shadowCoords;

out vec4 outColor;

uniform sampler2D backgroundTexture;
uniform sampler2D rTexture;
uniform sampler2D gTexture;
uniform sampler2D bTexture;
uniform sampler2D blendMap;
uniform sampler2D shadowMap;

uniform vec3 lightColor[4];
uniform vec3 attenuation[4];
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColor;

const int pcfCount = 2;
const float totalTexels = (pcfCount * 2.0 + 1.0) * (pcfCount * 2.0 + 1.0);

void main(void) {

    float mapSize = 4096.0;
    float texelSize = 1.0 / mapSize;
    float total = 0.0;

    for(int x = -pcfCount; x <= pcfCount; x++) {
        for(int z = -pcfCount; z <= pcfCount; z++) {
            float objectNearestLight = texture(shadowMap, shadowCoords.xy + vec2(x, z) * texelSize).r;
            if(shadowCoords.z > objectNearestLight + 0.002) {
                total += 1.0;
            }
        }
    }

    total /= totalTexels;
    float lightFactor = 1.0 - (total * shadowCoords.w);

    vec4 blendMapColor = texture(blendMap, passTextureCoords);

    float backTextureAmount = 1 - (blendMapColor.r + blendMapColor.g + blendMapColor.b);
    vec2 tileCoords = passTextureCoords * 40.0;

    vec4 backgroundTextureColor = texture(backgroundTexture, tileCoords) * backTextureAmount;
    vec4 rTextureAmount = texture(rTexture, tileCoords) * blendMapColor.r;
    vec4 gTextureAmount = texture(gTexture, tileCoords) * blendMapColor.g;
    vec4 bTextureAmount = texture(bTexture, tileCoords) * blendMapColor.b;

    vec4 totalColor = backgroundTextureColor + rTextureAmount + gTextureAmount + bTextureAmount;

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitCamera = normalize(toCameraVector);

    vec3 totalDiffuse = vec3(0.0);
    vec3 totalSpecular = vec3(0.0);

    for(int i = 0; i < 4; i++) {
        float distance = length(toLightVector[i]);
        float attFactor = attenuation[i].x + attenuation[i].y * distance + attenuation[i].z * distance * distance;
        vec3 unitLight = normalize(toLightVector[i]);
        float nDot1 = dot(unitNormal, unitLight);
        float brightness = max(nDot1, 0.0);
        vec3 lightDirection = -unitLight;
        vec3 reflectedLight = reflect(lightDirection, unitNormal);
        float specularFactor = dot(reflectedLight, unitCamera);
        specularFactor = max(specularFactor, 0.0);
        float dampedFactor = pow(specularFactor, shineDamper);
        totalDiffuse = totalDiffuse + (brightness * lightColor[i]) / attFactor;
        totalSpecular = totalSpecular + (dampedFactor * reflectivity * lightColor[i]) / attFactor;
    }
    totalDiffuse = max(totalDiffuse * lightFactor, 0.4);

    outColor = vec4(totalDiffuse, 1.0) * totalColor + vec4(totalSpecular, 1.0);
    outColor = mix(vec4(skyColor, 1.0), outColor, visibility);
}