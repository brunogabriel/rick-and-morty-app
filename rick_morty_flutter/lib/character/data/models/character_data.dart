import 'package:equatable/equatable.dart';
import 'package:rick_morty_flutter/character/data/service/response/character_response.dart';

class CharacterData extends Equatable {
  final int id;
  final String name;
  final String status;
  final String type;
  final String gender;
  final String image;

  const CharacterData(
      this.id, this.name, this.status, this.type, this.gender, this.image);

  @override
  List<Object> get props => [id, name, status, type, gender, image];

  factory CharacterData.fromResponse(CharacterResponse response) =>
      CharacterData(
        response.id,
        response.name,
        response.status,
        response.type,
        response.gender,
        response.image,
      );
}
