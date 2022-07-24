import 'package:equatable/equatable.dart';
import 'package:flutter/foundation.dart';
// ignore: depend_on_referenced_packages
import 'package:json_annotation/json_annotation.dart';
import 'package:rick_morty_flutter/shared/network/response/api_info_response.dart';
import 'package:rick_morty_flutter/shared/network/response/api_result_response.dart';

part 'character_response.g.dart';

@immutable
@JsonSerializable()
class CharacterResponse extends Equatable {
  final int id;
  final String name;
  final String status;
  final String type;
  final String gender;
  final String image;
  final CharacterLocationResponse location;

  const CharacterResponse(
    this.id,
    this.name,
    this.status,
    this.type,
    this.gender,
    this.image,
    this.location,
  );

  @override
  List<Object> get props => [id, name, status, type, gender, image, location];

  factory CharacterResponse.fromJson(Map<String, dynamic> json) =>
      _$CharacterResponseFromJson(json);
}

@immutable
@JsonSerializable()
class CharacterLocationResponse extends Equatable {
  final String name;
  final String url;

  const CharacterLocationResponse(this.name, this.url);

  @override
  List<Object> get props => [name, url];

  factory CharacterLocationResponse.fromJson(Map<String, dynamic> json) =>
      _$CharacterLocationResponseFromJson(json);
}

@immutable
@JsonSerializable()
class CharacterApiResultResponse extends ApiResultResponse<CharacterResponse> {
  const CharacterApiResultResponse({
    required super.info,
    required super.results,
  });

  @override
  List<Object> get props => [info, results];

  factory CharacterApiResultResponse.fromJson(Map<String, dynamic> json) =>
      _$CharacterApiResultResponseFromJson(json);
}
