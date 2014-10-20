package ru.spb.ipo.wisetaks2.paints.ru

import ru.spb.ipo.wisetaks2.*
import ru.spb.ipo.engine.sets.DecartSet
import ru.spb.ipo.engine.sets.NumericSet
import ru.spb.ipo.engine.elements.ContainerElement


  задача {
	название = "Краски"

        описание {
            """Имеется k красок. Сколькими способами можно раскрасить стороны
                данного правильного n-угольника так,
                чтобы соседние стороны были окрашены в разные цвета""""
        }

        верификатор(javaClass<CountVerifier<ContainerElement, SourceSet<ContainerElement>>>()) {
            val n = параметр("n", 4, 6)
            val k = параметр("k", 4, 6)

            исходноеМножество = DecartSet((1..n).map { NumericSet(1, k) }.toList()).toSet()

            filter = @l {(p): Boolean ->
                for (i in 1..p.getLength() + 1) {
                    if (p[i] == p[i + 1]) {
                        return@l false
                    }
                }

                true
            }
        }
    }

