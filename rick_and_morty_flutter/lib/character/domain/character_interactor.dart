import 'package:injectable/injectable.dart';
import 'package:rick_and_morty_flutter/character/data/repository/character_repository.dart';

import '../../shared/network/api_response.dart';
import '../data/response/character_response.dart';

@Injectable(as: ICharacterInteractor)
class CharacterInteractor implements ICharacterInteractor {
  final ICharacterRepository _repository;

  const CharacterInteractor(this._repository);

  @override
  Future<ApiResponse<CharacterResponse>> getCharacters(int page) =>
      _repository.getCharacters(page);
}

abstract class ICharacterInteractor {
  Future<ApiResponse<CharacterResponse>> getCharacters(int page);
}
