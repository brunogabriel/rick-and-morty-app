// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'api_info_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ApiInfoResponse _$ApiInfoResponseFromJson(Map<String, dynamic> json) =>
    ApiInfoResponse(
      json['count'] as int,
      json['pages'] as int,
      json['next'] as String?,
      json['previous'] as String?,
    );

Map<String, dynamic> _$ApiInfoResponseToJson(ApiInfoResponse instance) =>
    <String, dynamic>{
      'count': instance.count,
      'pages': instance.pages,
      'next': instance.next,
      'previous': instance.previous,
    };
