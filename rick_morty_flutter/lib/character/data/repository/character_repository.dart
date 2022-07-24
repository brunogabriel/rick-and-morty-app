import 'package:injectable/injectable.dart';
import 'package:rick_morty_flutter/character/data/models/character_data.dart';
import 'package:rick_morty_flutter/character/data/service/character_service.dart';
import 'package:rick_morty_flutter/character/data/service/response/character_response.dart';

@Injectable(as: ICharacterRepository)
class CharacterRepository extends ICharacterRepository {
  final ICharacterService _service;

  CharacterRepository(this._service);

  @override
  Future<List<CharacterData>> getCharacters(int page) => _service
      .getCharacters(page)
      .then((value) => _fromResponse(value.results));

  List<CharacterData> _fromResponse(List<CharacterResponse> responses) =>
      responses.map((e) => CharacterData.fromResponse(e)).toList();
}

abstract class ICharacterRepository {
  Future<List<CharacterData>> getCharacters(int page);
}
