package com.example.educationapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.educationapp.model.Document
import java.time.LocalDate

// Placeholder data classes
data class Reminder(
    val id: String,
    val title: String,
    val date: LocalDate,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // Placeholder data
    val recentDocuments = listOf(
        Document("1", "Mathematics Notes", "Content 1", "Summary of math concepts"),
        Document("2", "Physics Formula", "Content 2", "Important physics formulas"),
        Document("3", "History Essay", "Content 3", "World War II essay"),
        Document("4", "Chemistry Lab", "Content 4", "Lab experiment notes"),
        Document("5", "Literature Review", "Content 5", "Shakespeare analysis")
    )

    val reminders = listOf(
        Reminder("1", "Math Exam", LocalDate.now().plusDays(5), "Chapter 1-5"),
        Reminder("2", "Physics Project", LocalDate.now().plusDays(10), "Due next week"),
        Reminder("3", "Study Group", LocalDate.now().plusDays(2), "Review session")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Welcome Section
        Text(
            text = "Welcome back!",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        // Displaying current login streak
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Streak",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "1 Day Streak!",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Shows the user's recent activity (most recently accessed documents)
        Text(
            text = "Recent Activity",
            style = MaterialTheme.typography.titleLarge
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recentDocuments) { document ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(120.dp),
                    onClick = { /* TODO: Navigate to document */ }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = document.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = document.summary ?: "No summary available",
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Allows users to set reminders
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Reminders",
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(onClick = { /* TODO: Add reminder */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Add reminder")
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        reminders.forEach { reminder ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                onClick = { /* TODO: Edit reminder */ }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = reminder.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Due: ${reminder.date}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = reminder.description,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
} 