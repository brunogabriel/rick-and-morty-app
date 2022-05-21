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
    );

Map<String, dynamic> _$CharacterResponseToJson(CharacterResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'status': instance.status,
      'type': instance.type,
      'gender': instance.gender,
      'image': instance.image,
    };

CharacterApiResponse _$CharacterApiResponseFromJson(
        Map<String, dynamic> json) =>
    CharacterApiResponse(
      Info.fromJson(json['info'] as Map<String, dynamic>),
      (json['results'] as List<dynamic>)
          .map((e) => CharacterResponse.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$CharacterApiResponseToJson(
        CharacterApiResponse instance) =>
    <String, dynamic>{
      'info': instance.info,
      'results': instance.results,
    };
