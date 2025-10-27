package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.ferreteriamapocho.data.UserPrefs
import com.ferreteriamapocho.data.UserProfile
import kotlinx.coroutines.launch

@Composable
fun AccountScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val savedProfile by UserPrefs.profileFlow(context).collectAsState(initial = UserProfile())

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var editMode by remember { mutableStateOf(false) }

    LaunchedEffect(savedProfile) {
        name = savedProfile.name
        lastName = savedProfile.lastName
        email = savedProfile.email
        phone = savedProfile.phone
        address = savedProfile.address
        password = savedProfile.password
        editMode = savedProfile.isEmpty
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado superior
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Ferretería Mapocho", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            if (!savedProfile.isEmpty) {
                Text("Hola, ${savedProfile.name}", style = MaterialTheme.typography.titleMedium)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(if (savedProfile.isEmpty) "Registro de cliente" else "Mi cuenta",
            style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        // Campos
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            enabled = editMode
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth(),
            enabled = editMode
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            enabled = editMode
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            enabled = editMode
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth(),
            enabled = editMode
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            enabled = editMode,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(16.dp))

        // Botones
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (editMode) {
                Button(
                    onClick = {
                        scope.launch {
                            val profile = UserProfile(
                                name = name.trim(),
                                lastName = lastName.trim(),
                                email = email.trim(),
                                phone = phone.trim(),
                                address = address.trim(),
                                password = password.trim()
                            )
                            UserPrefs.save(context, profile)
                            editMode = false
                        }
                    },
                    enabled = name.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank()
                ) {
                    Text(if (savedProfile.isEmpty) "Registrarse" else "Guardar cambios")
                }
                OutlinedButton(onClick = {
                    name = savedProfile.name
                    lastName = savedProfile.lastName
                    email = savedProfile.email
                    phone = savedProfile.phone
                    address = savedProfile.address
                    password = savedProfile.password
                    editMode = false
                }) {
                    Text("Cancelar")
                }
            } else {
                OutlinedButton(onClick = { editMode = true }) {
                    Text("Editar")
                }
                OutlinedButton(onClick = {
                    scope.launch { UserPrefs.clear(context) }
                }) {
                    Text("Borrar cuenta")
                }
            }
        }
    }
}
