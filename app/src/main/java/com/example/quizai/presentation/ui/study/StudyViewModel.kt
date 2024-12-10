package com.example.quizai.presentation.ui.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizai.data.repository.QuizRepository
import com.example.quizai.data.repository.FlashcardRepository
import com.example.quizai.model.Quiz
import com.example.quizai.model.FlashcardSet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val flashcardRepository: FlashcardRepository
) : ViewModel() {
    val quizzes: StateFlow<List<Quiz>> = quizRepository.getAllQuizzes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
    fun loadQuiz(quizId: String): StateFlow<Quiz?> = quizRepository.getQuiz(quizId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
        
    fun getQuizHighScore(quizId: String): StateFlow<Int?> = 
        quizRepository.getQuizHighScore(quizId)
            .map { it?.highScore }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
            
    fun updateHighScore(quizId: String, score: Int, totalQuestions: Int) {
        viewModelScope.launch {
            quizRepository.updateHighScore(quizId, score, totalQuestions)
        }
    }
    
    val flashcardSets: StateFlow<List<FlashcardSet>> = flashcardRepository
        .getFlashcardSets()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getFlashcardSet(setId: String): StateFlow<FlashcardSet?> = 
        flashcardRepository.getFlashcardSet(setId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
} 