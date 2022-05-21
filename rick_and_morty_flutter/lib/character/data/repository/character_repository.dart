import 'package:injectable/injectable.dart';
import 'package:rick_and_morty_flutter/character/data/response/character_response.dart';
import 'package:rick_and_morty_flutter/character/data/service/character_service.dart';
import 'package:rick_and_morty_flutter/shared/network/api_response.dart';

@Injectable(as: ICharacterRepository)
class CharacterRepository implements ICharacterRepository {
  final ICharacterService _service;

  const CharacterRepository(this._service);

  @override
  Future<ApiResponse<CharacterResponse>> getCharacters(int page) =>
      _service.getCharacters(page);
}

abstract class ICharacterRepository {
  Future<ApiResponse<CharacterResponse>> getCharacters(int page);
}
