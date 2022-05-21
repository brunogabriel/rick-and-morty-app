import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:rick_and_morty_flutter/character/data/response/character_response.dart';
import 'package:rick_and_morty_flutter/shared/network/api_response.dart';

@Injectable(as: ICharacterService)
class CharacterService implements ICharacterService {
  final Dio _client;

  const CharacterService(this._client);

  @override
  Future<ApiResponse<CharacterResponse>> getCharacters(int page) => _client
      .get("/character/?page=$page")
      .then((value) => CharacterApiResponse.fromJson(value.data));
}

abstract class ICharacterService {
  Future<ApiResponse<CharacterResponse>> getCharacters(int page);
}
