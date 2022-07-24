import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:rick_morty_flutter/character/data/service/response/character_response.dart';

@Injectable(as: ICharacterService)
class CharacterService implements ICharacterService {
  final Dio _client;

  const CharacterService(this._client);

  @override
  Future<CharacterApiResultResponse> getCharacters(int page) => _client
      .get("/character/?page=$page")
      .then((value) => CharacterApiResultResponse.fromJson(value.data));
}

abstract class ICharacterService {
  Future<CharacterApiResultResponse> getCharacters(int page);
}
