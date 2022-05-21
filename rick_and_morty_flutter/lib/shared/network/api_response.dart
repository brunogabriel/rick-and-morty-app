// ignore: depend_on_referenced_packages
import 'package:json_annotation/json_annotation.dart';
part 'api_response.g.dart';

@JsonSerializable()
class Info {
  final int count;
  final int pages;
  final String? next;
  final String? previous;

  const Info(
    this.count,
    this.pages,
    this.next,
    this.previous,
  );

  factory Info.fromJson(Map<String, dynamic> json) => _$InfoFromJson(json);

  Map<String, dynamic> toJson() => _$InfoToJson(this);
}

abstract class ApiResponse<T> {
  final Info info;
  final List<T> results;

  const ApiResponse(
    this.info,
    this.results,
  );
}
