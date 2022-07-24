// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'character_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CharacterResponse _$CharacterResponseFromJson(Map<String, dynamic> json) =>
    CharacterResponse(
      json['id'] as int,
      json['name'] as String,
      json['status'] as String,
      json['type'] as String,
      json['gender'] as String,
      json['image'] as String,
      CharacterLocationResponse.fromJson(
          json['location'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$CharacterResponseToJson(CharacterResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'status': instance.status,
      'type': instance.type,
      'gender': instance.gender,
      'image': instance.image,
      'location': instance.location,
    };

CharacterLocationResponse _$CharacterLocationResponseFromJson(
        Map<String, dynamic> json) =>
    CharacterLocationResponse(
      json['name'] as String,
      json['url'] as String,
    );

Map<String, dynamic> _$CharacterLocationResponseToJson(
        CharacterLocationResponse instance) =>
    <String, dynamic>{
      'name': instance.name,
      'url': instance.url,
    };

CharacterApiResultResponse _$CharacterApiResultResponseFromJson(
        Map<String, dynamic> json) =>
    CharacterApiResultResponse(
      info: ApiInfoResponse.fromJson(json['info'] as Map<String, dynamic>),
      results: (json['results'] as List<dynamic>)
          .map((e) => CharacterResponse.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$CharacterApiResultResponseToJson(
        CharacterApiResultResponse instance) =>
    <String, dynamic>{
      'info': instance.info,
      'results': instance.results,
    };
