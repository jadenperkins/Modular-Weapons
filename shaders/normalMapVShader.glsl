#version 400 core

in vec3 position;
in vec2 textureCoordinates;
in vec3 normal;
in vec3 tangent;

out vec2 passTextureCoordinates;
out vec3 toLightVector[4];
out vec3 toCameraVector;
out float visibility;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPositionEyeSpace[4];

uniform int numberOfRows;
uniform vec2 offset;

uniform float density;
uniform float gradient;

uniform vec4 plane;

void main(void) {
	vec4 worldPosition = transformationMatrix * vec4(position,1.0);
	gl_ClipDistance[0] = dot(worldPosition, plane);
	mat4 modelViewMatrix = viewMatrix * transformationMatrix;
	vec4 positionRelativeToCam = modelViewMatrix * vec4(position,1.0);
	gl_Position = projectionMatrix * positionRelativeToCam;
	
	passTextureCoordinates = (textureCoordinates / numberOfRows) + offset;
	
	vec3 surfaceNormal = (modelViewMatrix * vec4(normal,0.0)).xyz;

	vec3 norm = normalize(surfaceNormal);
	vec3 tang = normalize((modelViewMatrix * vec4(tangent, 0.0)).xyz);
	vec3 bitang = normalize(cross(norm, tang));

	mat3 toTangentSpace = mat3(
	    tang.x, bitang.x, normal.x,
	    tang.y, bitang.y, normal.y,
	    tang.z, bitang.z, normal.z
	);

	for(int i=0;i<4;i++){
		toLightVector[i] = toTangentSpace * (lightPositionEyeSpace[i] - positionRelativeToCam.xyz);
	}
	toCameraVector = toTangentSpace * (-positionRelativeToCam.xyz);
	
	float distance = length(positionRelativeToCam.xyz);
	visibility = exp(-pow((distance*density),gradient));
	visibility = clamp(visibility,0.0,1.0);
	
}