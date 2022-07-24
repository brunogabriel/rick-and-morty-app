import 'package:injectable/injectable.dart';
import 'package:rick_morty_flutter/character/data/models/character_data.dart';
import 'package:rick_morty_flutter/character/data/repository/character_repository.dart';

@Injectable(as: ICharacterUseCase)
class CharacterUseCase extends ICharacterUseCase {
  final CharacterRepository _repository;

  CharacterUseCase(this._repository);

  @override
  Future<List<CharacterData>> getCharacters(int page) =>
      _repository.getCharacters(page);
}

abstract class ICharacterUseCase {
  Future<List<CharacterData>> getCharacters(int page);
}
