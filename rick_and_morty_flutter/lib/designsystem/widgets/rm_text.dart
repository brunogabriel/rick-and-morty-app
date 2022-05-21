import 'package:flutter/cupertino.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/colors.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/font_weight.dart';
import 'package:rick_and_morty_flutter/designsystem/constants/styles.dart';

class RMText extends StatelessWidget {
  final String text;
  final RMTextStyle style;
  final RMFontWeight fontWeight;
  final RMColor color;

  const RMText({
    super.key,
    required this.text,
    required this.style,
    this.fontWeight = RMFontWeight.regular,
    this.color = RMColor.black,
  });

  @override
  Widget build(BuildContext context) => Text(text,
      style: style.value
          .copyWith(fontWeight: fontWeight.value, color: color.value));
}
