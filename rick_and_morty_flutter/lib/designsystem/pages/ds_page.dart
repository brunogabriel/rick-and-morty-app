import 'package:flutter/material.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/colors.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/font_weight.dart';
import 'package:rick_and_morty_flutter/designsystem/widgets/rm_card.dart';
import 'package:rick_and_morty_flutter/designsystem/widgets/rm_text.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/styles.dart';

class DSPage extends StatelessWidget {
  const DSPage({Key? key}) : super(key: key);

  List<Widget> get _buildWidgets => [
        // heading
        const Padding(padding: EdgeInsets.only(top: 12)),
        const RMText(
          text: 'Heading',
          style: RMTextStyle.body20,
          color: RMColor.gray,
        ),
        const RMText(
          text: 'Heading 1 - Bold',
          style: RMTextStyle.heading,
          fontWeight: RMFontWeight.bold,
        ),
        const RMText(
          text: 'Heading 2 - Bold',
          style: RMTextStyle.heading2,
          fontWeight: RMFontWeight.bold,
        ),
        const RMText(text: 'Heading 2 - Regular', style: RMTextStyle.heading2),
        const RMText(
          text: 'Heading 3 - Regular',
          style: RMTextStyle.heading3,
          color: RMColor.accent,
        ),

        // body
        const Padding(padding: EdgeInsets.only(top: 12)),
        const RMText(
          text: 'Body',
          style: RMTextStyle.body20,
          color: RMColor.gray,
        ),
        const RMText(
          text: 'Body 20 - Bold',
          style: RMTextStyle.body20,
          fontWeight: RMFontWeight.bold,
        ),
        const RMText(
          text: 'Body 20 - Regular',
          style: RMTextStyle.body20,
          color: RMColor.accent,
        ),
        const RMText(
          text: 'Body 17 - Bold',
          style: RMTextStyle.body17,
          fontWeight: RMFontWeight.bold,
        ),
        const RMText(
          text: 'Body 17 - Regular',
          style: RMTextStyle.body17,
        ),
        const RMText(
          text: 'Body 15 - Regular',
          style: RMTextStyle.body15,
        ),

        // large
        const Padding(padding: EdgeInsets.only(top: 12)),
        const RMText(
          text: 'Large',
          style: RMTextStyle.body20,
          color: RMColor.gray,
        ),
        const RMText(
          text: 'Large - bold',
          style: RMTextStyle.large,
          fontWeight: RMFontWeight.bold,
        ),

        // caption
        const Padding(padding: EdgeInsets.only(top: 12)),
        const RMText(
          text: 'Caption',
          style: RMTextStyle.body20,
          color: RMColor.gray,
        ),
        const RMText(
          text: 'Caption 11 - Regular',
          style: RMTextStyle.caption11,
        ),
        const RMText(
          text: 'Caption 13 - Regular',
          style: RMTextStyle.caption13,
        ),

        // headline
        const Padding(padding: EdgeInsets.only(top: 12)),
        const RMText(
          text: 'Headline',
          style: RMTextStyle.body20,
          color: RMColor.gray,
        ),
        const RMText(
          text: 'Headline 15 - Regular',
          style: RMTextStyle.headline15,
        ),
        const RMText(
          text: 'Headline 13 - Regular',
          style: RMTextStyle.headling13,
        ),
        const RMText(
          text: 'Headline 11 - Regular',
          style: RMTextStyle.headline11,
        ),

        // cards
        const Padding(padding: EdgeInsets.only(top: 12)),
        const RMText(
          text: 'Cards',
          style: RMTextStyle.body20,
          color: RMColor.gray,
        ),
        Row(
          children: const [
            RmCard(
              title: 'Status',
              content: 'Peperonni Pizza',
              image: 'images/img267.jpeg',
            ),
            RmCard(
              title: 'Status',
              content: 'Planet Blue',
            ),
          ],
        ),
      ];

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: _buildWidgets,
    );
  }
}
