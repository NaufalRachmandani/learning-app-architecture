package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import java.util.*

object DataDummy {

    fun generateDummyMovieList(): ArrayList<MovieItem> {
        val movies = ArrayList<MovieItem>()

        movies.add(
            MovieItem(
                "en",
                "The Croods: A New Age",
                "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
                "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
                8.1,
                529203
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Honest Thief",
                "/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
                "/tYkMtYPNpUdLdzGDUTC5atyMh9X.jpg",
                7.0,
                553604
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Tenet",
                "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "/wzJRB4MKi3yK138bJyuL9nx47y6.jpg",
                7.4,
                577922
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Wander",
                "/2AwPvNHphpZBJDqjZKVuMAbvS0v.jpg",
                "/wk58aoyWpMTVkKkdjw889XfWGdL.jpg",
                5.6,
                646593
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Greenland",
                "/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg",
                "/2Fk3AB8E9dYIBc2ywJkxk8BTyhc.jpg",
                7.2,
                524047
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Jiu Jitsu",
                "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "/jeAQdDX9nguP6YOX6QSWKDPkbBo.jpg",
                5.6,
                590706
            )
        )
        movies.add(
            MovieItem(
                "de",
                "Wir Können Nicht Anders",
                "/ajKpYK7XdzIYjy9Uy8nkgRboKyv.jpg",
                "/zMcEalkxEiRjvmijliLBk0sYern.jpg",
                4.5,
                765123
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Fatman",
                "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
                "/ckfwfLkl0CkafTasoRw5FILhZAS.jpg",
                5.9,
                602211
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Wonder Woman 1984",
                "/di1bCAfGoJ0BzNEavLsPyxQ2AaB.jpg",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                7.0,
                464052
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Arthur & Merlin: Knights of Camelot",
                "/chGTXsvn53XvEnvsJ9ZD9eiYKx9.jpg",
                "/sFLgXQGrSWxnjmPOpGKPApWNOUH.jpg",
                6.0,
                635237
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Monsters of Man",
                "/1f3qspv64L5FXrRy0MF8X92ieuw.jpg",
                "/zbD96UExL9hl8TNihhs16vTBPEn.jpg",
                7.7,
                733317
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Heavenquest: A Pilgrim's Progress",
                "/cLDPLia17AwMqSaRHccyAlInkch.jpg",
                "/cw8A0SprTxr7uSfcH7lwSRRhezJ.jpg",
                5.5,
                634244
            )
        )
        movies.add(
            MovieItem(
                "ko",
                "반도",
                "/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                "/d2UxKyaJ5GgzuHaSsWinFfv3g6L.jpg",
                6.9,
                581392
            )
        )
        movies.add(
            MovieItem(
                "en",
                "The Craft: Legacy",
                "/lhMIra0pqWNuD6CIXoTmGwZ0EBS.jpg",
                "/lIE7kfdLBRd0KENNtOaIqPPWNqh.jpg",
                6.2,
                590995
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Toys of Terror",
                "/c6hmAgPVXxZHwMHfS9z3W2n9Gz9.jpg",
                "/j6Er72CMHKiZgFr0HBMbuyj7Ssa.jpg",
                5.4,
                753926
            )
        )
        movies.add(
            MovieItem(
                "en",
                "The SpongeBob Movie: Sponge on the Run",
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                7.9,
                400160
            )
        )
        movies.add(
            MovieItem(
                "fr",
                "Les chevaliers du fiel dynamitent 2019",
                "/oh6AZL0nnQdnz8bazoDzTAVVHfB.jpg",
                "/tfxRXujXbi2esl8WLhdqzixjbIY.jpg",
                3.7,
                669444
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Chick Fight",
                "/4ZocdxnOO6q2UbdKye2wgofLFhB.jpg",
                "/iZ6wYtPGO6Hg4yP7O1ZXpITcx7V.jpg",
                5.7,
                682377
            )
        )
        movies.add(
            MovieItem(
                "en",
                "After We Collided",
                "/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                "/6hgItrYQEG33y0I7yP2SRl2ei4w.jpg",
                7.3,
                613504
            )
        )
        movies.add(
            MovieItem(
                "en",
                "Castle Freak",
                "/HJEosQRIdBzAHEdZJCUF7e5FmF.jpg",
                "/1WDITmJAHz4rAnjVv1XYN4IfjKq.jpg",
                4.8,
                593655
            )
        )

        return movies
    }

    fun generateDummyTvList(): ArrayList<TvItem> {

        val tv = ArrayList<TvItem>()

        tv.add(
            TvItem(
                "en",
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                "The Mandalorian",
                8.5,
                82856
            )
        )
        tv.add(
            TvItem(
                "en",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                "The Good Doctor",
                8.6,
                71712
            )
        )
        tv.add(
            TvItem(
                "en",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "Grey's Anatomy",
                8.1,
                1416
            )
        )
        tv.add(
            TvItem(
                "en",
                "/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg",
                "/Wu8kh7oyvaIfkNyMJyJHCamh5L.jpg",
                "Selena: The Series",
                7.4,
                97180
            )
        )
        tv.add(
            TvItem(
                "en",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                "Lucifer",
                8.5,
                63174
            )
        )
        tv.add(
            TvItem(
                "en",
                "/4G2aJJs1lXoS0n6ftZglkXtZpc6.jpg",
                "/jRpgMeHERgyC7j53pLreJPZMAO6.jpg",
                "Industry",
                7.1,
                90812
            )
        )
        tv.add(
            TvItem(
                "en",
                "/gmL6MSH3jK2T7zYvzo9dIZb393c.jpg",
                "/f8RGyElWxLiL1lLzC9KcPuUZIzf.jpg",
                "30 Monedas",
                6.5,
                89844
            )
        )
        tv.add(
            TvItem(
                "en",
                "/g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg",
                "/9yKCJTOh9m3Lol2RY3kw99QPH6x.jpg",
                "His Dark Materials",
                8.1,
                68507
            )
        )
        tv.add(
            TvItem(
                "en",
                "/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
                "/9hvhGtcsGhQY58pukw8w55UEUbL.jpg",
                "Riverdale",
                8.6,
                69050
            )
        )
        tv.add(
            TvItem(
                "en",
                "/uTSLeQTeHevt4fplegmQ6bOnE0Z.jpg",
                "/6lOtF3yx8iurvaBVz1ZVhwcRgmD.jpg",
                "SEAL Team",
                7.8,
                71789
            )
        )
        tv.add(
            TvItem(
                "en",
                "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
                "/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg",
                "The Queen's Gambit",
                8.7,
                87739
            )
        )
        tv.add(
            TvItem(
                "en",
                "/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
                "/92B6765nyQkIW8yLWt3pNcS6Cip.jpg",
                "Fear the Walking Dead",
                7.5,
                62286
            )
        )
        tv.add(
            TvItem(
                "en",
                "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                "/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
                "The Simpsons",
                7.8,
                456
            )
        )
        tv.add(
            TvItem(
                "en",
                "/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                "/mE3zzMkpP8yqlkzdjPsQmJHceoe.jpg",
                "The Umbrella Academy",
                8.7,
                75006
            )
        )
        tv.add(
            TvItem(
                "en",
                "/aBkVgChtyyJaHyZh1gfd8DbzQon.jpg",
                "/k7T9xRyzP41wBVNyNeLmh8Ch7gD.jpg",
                "The Vampire Diaries",
                8.3,
                18165
            )
        )
        tv.add(
            TvItem(
                "en",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "Game of Thrones",
                8.4,
                1399
            )
        )
        tv.add(
            TvItem(
                "en",
                "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
                "/hTExot1sfn7dHZjGrk0Aiwpntxt.jpg",
                "The 100",
                7.8,
                48866
            )
        )
        tv.add(
            TvItem(
                "en",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "/nVRyd8hlg0ZLxBn9RaI7mUMQLnz.jpg",
                "Supernatural",
                8.1,
                1622
            )
        )
        tv.add(
            TvItem(
                "en",
                "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
                "The Boys",
                8.5,
                76479
            )
        )
        tv.add(
            TvItem(
                "en",
                "/oogunE22HDTcTxFakKQbwqfw1qo.jpg",
                "/wgEWTYAAY7F04o4F20j0L1DGB8i.jpg",
                "I Am...",
                5.2,
                91605
            )
        )

        return tv
    }

    fun generateDummyMovie(): DetailMovieResponse {
        return DetailMovieResponse(
            "en",
            "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
            2712.592,
            529203,
            "After leaving their cave, the Croods encounter their biggest threat since leaving: another family called the Bettermans, who claim and show to be better and evolved. Grug grows suspicious of the Betterman parents, Phil and Hope,  as they secretly plan to break up his daughter Eep with her loving boyfriend Guy to ensure that their daughter Dawn has a loving and smart partner to protect her.",
            "The Croods: A New Age",
            "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
            8.1,
            "Released"
        )
    }

    fun generateDummyTv(): DetailTvResponse {
        return DetailTvResponse(
            "en",
            16,
            "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
            1866.12,
            82856,
            "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "The Mandalorian",
            8.5,
            "Returning Series"
        )
    }
}