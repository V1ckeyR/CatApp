package ua.kpi.comsys.lab12

import java.util.*

// Частина 1

// Дано рядок у форматі "Student1 - Group1; Student2 - Group2; ..."

const val studentsStr = "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82"

// Завдання 1
// Заповніть словник, де:
// - ключ – назва групи
// - значення – відсортований масив студентів, які відносяться до відповідної групи

fun task1(str: String) = str.split("; ")
        .map { it.split(" - ") }
        .groupBy { it.last() }
        .mapValues { (_, v) -> v.map { it.first() }.sorted() }

//Дано масив з максимально можливими оцінками

val points = listOf(12, 12, 12, 12, 12, 12, 12, 16)

// Завдання 2
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – масив з оцінками студента (заповніть масив випадковими значеннями, використовуючи функцію `randomValue(maxValue: Int) -> Int`)

fun randomValue(maxValue: Int) : Int {
    return when (Random().nextInt(6)) {
        1 -> Math.ceil(maxValue * 0.7).toInt()
        2 -> Math.ceil(maxValue * 0.9).toInt()
        3,4,5 -> maxValue
        else -> 0
    }
}

fun task2(students: Map<String, List<String>>) = students.mapValues {
    (_, v) -> v.map { it to points.map { point -> randomValue(point) } }
        .groupBy { it.first }
        .mapValues { (_, v) -> v.map { it.second }[0] }
}

// Завдання 3
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – сума оцінок студента

fun task3(points: Map<String, Map<String, List<Int>>>) = points.mapValues { it ->
    it.value.mapValues { it.value.sum() }
}

// Завдання 4
// Заповніть словник, де:
// - ключ – назва групи
// - значення – середня оцінка всіх студентів групи

fun task4(points: Map<String, Map<String, Int>>) = points.mapValues { it ->
    it.value.map { it.value }.average()
}

// Завдання 5
// Заповніть словник, де:
// - ключ – назва групи
// - значення – масив студентів, які мають >= 60 балів

fun task5(points: Map<String, Map<String, Int>>) = points.mapValues { it ->
    it.value.filter { it.value >= 60 }.map { it.key }
}

// Частина 2

class TimeVR (hours: Int = 0, minutes: Int = 0, seconds: Int = 0) {
    constructor(date: Date) : this(date.hours, date.minutes, date.seconds)

    private val h = if (hours in 0..23) hours else error("Wrong hours")
    private val m = if (minutes in 0..59) minutes else error("Wrong minutes")
    private val s = if (seconds in 0..59) seconds else error("Wrong seconds")

    override fun toString(): String {
        val hh = when (h) {
            in 0..9 -> "0$h"
            in 10..12 -> "$h"
            in 13..21 -> "0${h - 12}"
            else -> "${h - 12}"
        }
        val mm = if (m >= 10) "$m" else "0$m"
        val ss = if (s >= 10) "$s" else "0$s"
        val zz = if (h > 12) "PM" else "AM"
        return "$hh:$mm:$ss $zz"
    }

    operator fun plus(obj: TimeVR): TimeVR {
        var sumH = this.h + obj.h
        var sumM = this.m + obj.m
        var sumS = this.s + obj.s

        if (sumS >= 60) {
            sumM += 1
            sumS -= 60
        }
        if (sumM >= 60) {
            sumH += 1
            sumM -= 60
        }
        if (sumH >= 24) { sumH -= 24 }
        return TimeVR(sumH, sumM, sumS)
    }

    operator fun minus(obj: TimeVR): TimeVR {
        var defH = this.h - obj.h
        var defM = this.m - obj.m
        var defS = this.s - obj.s

        if (defS < 0) {
            defM -= 1
            defS += 60
        }
        if (defM < 0) {
            defH -= 1
            defM += 60
        }
        if (defH < 0) { defH += 24 }
        return TimeVR(defH, defM, defS)
    }

    companion object {
        fun sum(obj1: TimeVR, obj2: TimeVR) = obj1 + obj2

        fun def(obj1: TimeVR, obj2: TimeVR) = obj1 - obj2
    }
}

fun main() {
    println("Завдання 1")
    val studentsGroups = task1(studentsStr)
    println(studentsGroups)
    println("Завдання 2")
    val studentPoints = task2(studentsGroups)
    println(studentPoints)
    println("Завдання 3")
    val sumPoints = task3(studentPoints)
    println(sumPoints)
    println("Завдання 4")
    val groupAvg = task4(sumPoints)
    println(groupAvg)
    println("Завдання 5")
    val passedPerGroup = task5(sumPoints)
    println(passedPerGroup)
    println("Частина 2")
    val obj1 = TimeVR(23,59,59)
    val obj2 = TimeVR(12, 0, 1)
    val obj3 = TimeVR(Date(0,0,0,0,0,0))
    val obj4 = TimeVR(Date(0,0,0,0,0,1))
    println("$obj1 + $obj2 = ${obj1 + obj2}")
    println(TimeVR.sum(obj1, obj2))
    println("$obj3 - $obj4 = ${obj3 - obj4}")
    println(TimeVR.def(obj3, obj4))
}
