package ua.kpi.comsys.lab12

import java.text.SimpleDateFormat
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
            else -> "0${h - 12}"
        }
        val mm = if (m >= 10) "$m" else "0$m"
        val ss = if (s >= 10) "$s" else "0$s"
        val zz = if (h > 12) "PM" else "AM"
        return "$hh:$mm:$ss $zz"
    }
}

fun main() {
//    println("Завдання 1")
//    val studentsGroups = task1(studentsStr)
//    println(studentsGroups)
//    println("Завдання 2")
//    val studentPoints = task2(studentsGroups)
//    println(studentPoints)
//    println("Завдання 3")
//    val sumPoints = task3(studentPoints)
//    println(sumPoints)
//    println("Завдання 4")
//    val groupAvg = task4(sumPoints)
//    println(groupAvg)
//    println("Завдання 5")
//    val passedPerGroup = task5(sumPoints)
//    println(passedPerGroup)
//    println("Частина 2")
    val obj = TimeVR(20,20,30)
    println(obj)
}
