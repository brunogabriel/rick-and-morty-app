import 'package:flutter/cupertino.dart';

enum RMFontWeight {
  regular(FontWeight.w400),
  bold(FontWeight.w700),
  semiBold(FontWeight.w600);

  final FontWeight value;

  const RMFontWeight(this.value);
}
