import 'package:equatable/equatable.dart';
import 'package:rick_morty_flutter/shared/network/response/api_info_response.dart';

abstract class ApiResultResponse<T> extends Equatable {
  final ApiInfoResponse info;
  final List<T> results;

  const ApiResultResponse({
    required this.info,
    required this.results,
  });

  @override
  List<Object> get props => [info, results];
}
