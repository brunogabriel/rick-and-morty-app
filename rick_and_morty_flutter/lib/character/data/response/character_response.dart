import 'package:rick_and_morty_flutter/shared/network/api_response.dart';
// ignore: depend_on_referenced_packages
import 'package:json_annotation/json_annotation.dart';

part 'character_response.g.dart';

@JsonSerializable()
class CharacterResponse {
  final int id;
  final String name;
  final String status;
  final String type;
  final String gender;
  final String image;

  const CharacterResponse(
    this.id,
    this.name,
    this.status,
    this.type,
    this.gender,
    this.image,
  );

  factory CharacterResponse.fromJson(Map<String, dynamic> json) =>
      _$CharacterResponseFromJson(json);

  Map<String, dynamic> toJson() => _$CharacterResponseToJson(this);
}

@JsonSerializable()
class CharacterApiResponse extends ApiResponse<CharacterResponse> {
  CharacterApiResponse(super.info, super.results);

  factory CharacterApiResponse.fromJson(Map<String, dynamic> json) =>
      _$CharacterApiResponseFromJson(json);

  Map<String, dynamic> toJson() => _$CharacterApiResponseToJson(this);
}
