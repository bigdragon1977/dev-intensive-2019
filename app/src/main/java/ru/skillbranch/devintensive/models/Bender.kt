package ru.skillbranch.devintensive.models

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question

    }
    fun listenAnswer(answer:String): Pair<String, Triple<Int, Int, Int>> {
        fun validateAnswer(answer: String): Boolean {


            return when(question) {
                Question.NAME -> "^[A-ZА-Я].*".toRegex().matches(answer)
                Question.PROFESSION -> "^[a-zа-я].*".toRegex().matches(answer)
                Question.MATERIAL -> "^[^0-9]{1,}$".toRegex().matches(answer)
                Question.BDAY -> "^[0-9]{4,}".toRegex().matches(answer)
                Question.SERIAL -> "^[0-9]{7}$".toRegex().matches(answer)
                else -> true
            }

        }

        return when(question) {
            Question.NAME ->
                if (validateAnswer(answer) && question.answer.contains(answer)) {
                    question = question.nextQuestion()
                    "Отлично - ты справился\n${question.question}" to status.color
                } else if (validateAnswer(answer) && !question.answer.contains(answer) && (status.color != Status.CRITICAL.color)) {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                } else if ((!validateAnswer(answer) || !question.answer.contains(answer)) && (status.color == Status.CRITICAL.color)) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
                }
            Question.PROFESSION ->
                if (validateAnswer(answer) && question.answer.contains(answer)) {
                    question = question.nextQuestion()
                    "Отлично - ты справился\n${question.question}" to status.color
                }  else if (validateAnswer(answer) && !question.answer.contains(answer)) {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                } else if ((!validateAnswer(answer) || !question.answer.contains(answer)) && (status.color == Status.CRITICAL.color)) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
                }
            Question.MATERIAL ->
                if (validateAnswer(answer) && question.answer.contains(answer)) {
                    question = question.nextQuestion()
                    "Отлично - ты справился\n${question.question}" to status.color
                }  else if (validateAnswer(answer) && !question.answer.contains(answer)) {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                } else if ((!validateAnswer(answer) || !question.answer.contains(answer)) && (status.color == Status.CRITICAL.color) ) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Материал не должен содержать цифр\n${question.question}" to status.color
                }
            Question.BDAY ->
                if (validateAnswer(answer) && question.answer.contains(answer)) {
                    question = question.nextQuestion()
                    "Отлично - ты справился\n${question.question}" to status.color
                } else if ((!validateAnswer(answer) || !question.answer.contains(answer)) && (status.color == Status.CRITICAL.color)) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }  else if (validateAnswer(answer) && !question.answer.contains(answer) && (status.color != Status.CRITICAL.color)) {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                } else {
                    "Год моего рождения должен содержать только цифры\n${question.question}" to status.color
                }
            Question.SERIAL ->
                if (validateAnswer(answer) && question.answer.contains(answer)) {
                    question = question.nextQuestion()
                    "Отлично - ты справился\n${question.question}" to status.color
                } else if ((!validateAnswer(answer) || !question.answer.contains(answer)) && (status.color == Status.CRITICAL.color)) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }  else if (validateAnswer(answer) && !question.answer.contains(answer) && (status.color != Status.CRITICAL.color)) {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                } else {
                    "Серийный номер содержит только цифры, и их 7\n${question.question}" to status.color
                }
            Question.IDLE -> {
                    question = Question.IDLE
                    //status = Status.NORMAL
                    question.question to status.color
                }

        }
    }
    enum class Status(var color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }
    enum class Question(val question: String, var answer:List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "Bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал","дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}