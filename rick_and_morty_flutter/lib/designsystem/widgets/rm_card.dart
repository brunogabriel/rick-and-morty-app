import 'package:flutter/material.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/colors.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/font_weight.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/spacing.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/styles.dart';
import 'package:rick_and_morty_flutter/designsystem/widgets/rm_text.dart';

enum RMCardImageType { local, network }

class RmCard extends StatelessWidget {
  final String title;
  final String content;
  final String? image;
  final RMCardImageType imageType;

  const RmCard({
    super.key,
    required this.title,
    required this.content,
    this.image,
    this.imageType = RMCardImageType.local,
  });

  Widget _buildImage(String imagePath) => ClipRRect(
      borderRadius: BorderRadius.only(
        topLeft: Radius.circular(RMSpacing.insetMD.value),
        topRight: Radius.circular(RMSpacing.insetMD.value),
      ),
      child: imageType == RMCardImageType.local
          ? Image.asset(imagePath, fit: BoxFit.fitWidth, height: 140)
          : Image.network(imagePath, fit: BoxFit.fitWidth, height: 140));

  @override
  Widget build(BuildContext context) {
    var imagePath = image ?? "";

    return Card(
      shape: RoundedRectangleBorder(
        side: BorderSide(color: RMColor.gray5.value, width: 1),
        borderRadius: BorderRadius.circular(RMSpacing.insetMD.value),
      ),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          imagePath.isNotEmpty
              ? _buildImage(imagePath)
              : const SizedBox.shrink(),
          Padding(
            padding: const EdgeInsets.all(12),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                RMText(text: title, style: RMTextStyle.caption11),
                RMText(
                  text: content,
                  style: RMTextStyle.body17,
                  fontWeight: RMFontWeight.bold,
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
