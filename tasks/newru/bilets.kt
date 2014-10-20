package ru.spb.ipo.wisetaks2.tasks.ru

import ru.spb.ipo.wisetaks2.*
import ru.spb.ipo.engine.elements.ContainerElement
import ru.spb.ipo.engine.sets.DecartSet
import ru.spb.ipo.engine.sets.NumericSet

/**
 * Created by mike on 10/14/14.
 */
 задача {

        название = "Cчастливые билеты"

        val основание = параметр("основание", Value(5, "пяти"), Value(9, "девяти"))
        val полдлины = параметр("полдлины", Value(2, "двух"), Value(3, "трех"))

        описание {
            """Трамвайный билет называется счастливым по-питерски,
            если сумма первых ${полдлины.текст} цифр равна сумме последних
            ${полдлины.текст} цифр. Трамвайный билет называется счастливым
            по-московски, если сумма его цифр, стоящих на четных местах,
            равна сумме цифр, стоящих на нечетных местах.
            Сколько существует билетов, счастливых и по питерски и по московски,
            если для записи билета используются цифры от нуля до ${основание.текст}?"""
        }

        верификатор(javaClass<CountVerifier<ContainerElement, SourceSet<ContainerElement>>>()) {
            исходноеМножество = DecartSet(NumericSet(0, основание.значение).times(2 * полдлины.значение)).toSet()
            filter = {(p) ->
                var left = 0;
                var right = 0

                var left2 = 0;
                var right2 = 0;
                for (i in 1..p.getLength()) {
                    val value = p[i].getInt()
                    if (i <= p.getLength() / 2) {
                        left += value
                    } else {
                        right += value
                    }

                    if (i % 2 == 0)
                        left2 += value
                    else {
                        right2 += value
                    }
                }

                left == right && left2 == right2
            }
        }
    };

