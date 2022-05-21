import 'package:flutter/cupertino.dart';
import 'package:get_it/get_it.dart';
import 'package:rick_and_morty_flutter/character/data/response/character_response.dart';
import 'package:rick_and_morty_flutter/character/domain/character_interactor.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/font_weight.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/spacing.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/styles.dart';
import 'package:rick_and_morty_flutter/designsystem/widgets/rm_card.dart';
import 'package:rick_and_morty_flutter/designsystem/widgets/rm_text.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';

class CharacterPage extends StatefulWidget {
  const CharacterPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _CharacterState();
}

class _CharacterState extends State<CharacterPage> {
  // TODO: migrate this rule to a custom ViewModel
  late PagingController<int, CharacterResponse> _pagingController;
  late ICharacterInteractor _interactor;

  @override
  void initState() {
    _pagingController = PagingController(firstPageKey: 0);
    _interactor = GetIt.I.get();
    _pagingController.addPageRequestListener((pageKey) {
      _fetchPage(pageKey);
    });
    super.initState();
  }

  Future<void> _fetchPage(int page) async {
    try {
      final response = await _interactor.getCharacters(page);
      final lastPage = response.info.next == null;

      if (lastPage) {
        _pagingController.appendLastPage(response.results);
      } else {
        _pagingController.appendPage(response.results, page + 1);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  Widget _buildGrid() {
    return PagedGridView<int, CharacterResponse>(
      showNewPageProgressIndicatorAsGridChild: false,
      showNewPageErrorIndicatorAsGridChild: false,
      showNoMoreItemsIndicatorAsGridChild: false,
      pagingController: _pagingController,
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        childAspectRatio: 1.0,
        crossAxisSpacing: RMSpacing.insetMD.value,
        mainAxisSpacing: RMSpacing.insetLG.value,
        crossAxisCount: 2,
      ),
      builderDelegate: PagedChildBuilderDelegate<CharacterResponse>(
        itemBuilder: (context, item, index) => RmCard(
          title: item.status,
          content: item.name,
          image: item.image,
          imageType: RMCardImageType.network,
          onTap: () {
            // Do nothing
          },
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: EdgeInsets.all(RMSpacing.insetMD.value),
          child: const RMText(
            text: 'Character',
            style: RMTextStyle.large,
            fontWeight: RMFontWeight.bold,
          ),
        ),
        Expanded(child: _buildGrid()),
      ],
    );
  }
}
