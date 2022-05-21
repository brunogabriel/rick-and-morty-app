import 'package:flutter/material.dart';

enum RMColor {
  // colors
  accent(Color(0xff5856d6)),
  adding(Color(0xff34c759)),
  destructive(Color(0xffff3b30)),
  black(Color(0xff081F32)),
  white(Color(0xffffffff)),
  gray(Color(0xffc7c7cc)),
  // gray
  gray1(Color(0xff8e8e93)),
  gray2(Color(0xffaeaeb2)),
  gray3(Color(0xffc7c7cc)),
  gray4(Color(0xffd1d1d6)),
  gray5(Color(0xffe5e5ea)),
  gray6(Color(0xfff2f2f7));

  final Color value;
  const RMColor(this.value);
}
