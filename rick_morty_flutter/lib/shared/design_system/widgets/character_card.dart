import 'package:flutter/material.dart';
import 'package:rick_morty_flutter/shared/design_system/constants.dart';

class CharacterCard extends StatelessWidget {
  final GestureTapCallback? onTap;
  final String url;

  const CharacterCard({Key? key, this.onTap, required this.url})
      : super(key: key);

  Widget _buildImage(BuildContext context, String url) {
    const imageRadius = Radius.circular(AppBorder.defaultValue);
    return ClipRRect(
      borderRadius: const BorderRadius.only(
          topLeft: imageRadius, bottomLeft: imageRadius),
      child: SizedBox(
        width: MediaQuery.of(context).size.height * 0.30,
        child: Image.network(
          url,
          fit: BoxFit.fitWidth,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    BorderRadius borderRadius = BorderRadius.circular(AppBorder.defaultValue);
    TextTheme textTheme = Theme.of(context).textTheme;
    return Card(
      shape: RoundedRectangleBorder(borderRadius: borderRadius),
      child: InkWell(
        customBorder: RoundedRectangleBorder(borderRadius: borderRadius),
        onTap: onTap,
        child: Row(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            _buildImage(context, url),
            Flexible(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Cirque du Soleil Zumanity Member asasasasw asasasasw asasasasw asasasasw asasasaswasasasaswasasasaswasasasaswasasasasw',
                      overflow: TextOverflow.ellipsis,
                      maxLines: 3,
                      style: textTheme.headline6
                          ?.copyWith(fontWeight: FontWeight.bold),
                    ),
                    Row(
                      children: [
                        Container(
                          height: 8,
                          width: 8,
                          decoration: BoxDecoration(
                              color: Colors.green, shape: BoxShape.circle),
                        ),
                        SizedBox(
                          width: 4,
                        ),
                        Text('Alive - Human'),
                      ],
                    ),
                    SizedBox(
                      height: 16,
                    ),
                    Text(
                      'Last known location:',
                      style: textTheme.bodySmall,
                    ),
                    Text('Post-Apocalyptic Earth'),
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
