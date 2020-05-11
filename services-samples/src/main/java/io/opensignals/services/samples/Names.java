/*
 * Copyright © 2020 OpenSignals Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package io.opensignals.services.samples;

import io.opensignals.services.Services;
import io.opensignals.services.Services.Name;

import java.util.stream.Collectors;

import static io.opensignals.services.samples.Strings.IO_OPENSIGNALS;

final class Names {

  private static void concatenation () {

    final Name prefix =
      Services.name (
        IO_OPENSIGNALS
      );

    final Name one =
      prefix.name (
        Strings.ONE
      );

    assert
      one
        .getPrefix ()
        .isPresent ();

    assert
      one
        .getPrefix ()
        .map ( Name::getValue )
        .filter ( Strings.OPENSIGNALS::equals )
        .isPresent ();

    assert
      prefix ==
        one
          .getPrefix ()
          .orElse (
            null
          );

  }

  private static void equality () {

    final Name name =
      Services.name (
        Strings.IO_OPENSIGNALS_ONE
      );

    assert
      name.equals (
        Services
          .name ( Strings.IO )
          .name ( Strings.OPENSIGNALS )
          .name ( Strings.ONE )
      );

    assert
      name ==
        Services
          .name ( Strings.IO )
          .name ( Strings.OPENSIGNALS )
          .name ( Strings.ONE );

    assert
      name ==
        Services
          .name ( IO_OPENSIGNALS )
          .name ( Strings.ONE );

  }

  private static void iteration () {

    final Name name =
      Services.name (
        Strings.IO_OPENSIGNALS_ONE
      );

    assert
      name
        .toString ()
        .equals (
          Strings.IO_OPENSIGNALS_ONE
        );

    assert
      "one -> opensignals -> io"
        .equals (
          name
            .stream ()
            .map ( Name::getValue )
            .collect ( Collectors.joining ( " -> " ) )
        );

    assert
      "one -> opensignals -> io"
        .equals (
          name
            .foldFrom (
              n ->
                new StringBuilder (
                  n.getValue ()
                ),
              ( sb, n ) ->
                sb.append (
                  " -> "
                ).append (
                  n.getValue ()
                )
            ).toString ()
        );

    assert
      "io -> opensignals -> one"
        .equals (
          name
            .foldTo (
              n ->
                new StringBuilder (
                  n.getValue ()
                ),
              ( sb, n ) ->
                sb.append (
                  " -> "
                ).append (
                  n.getValue ()
                )
            ).toString ()
        );

  }

  private static void path () {

    assert
      Strings.IO_OPENSIGNALS_ONE
        .equals (
          Services.name (
            IO_OPENSIGNALS
          ).name (
            Strings.ONE
          ).toString ()
        );

  }

  private Names () {}

  public static void main (
    final String[] args
  ) {

    concatenation ();
    equality ();
    iteration ();
    path ();

  }

}
