// screens/RegisterStep1.js
import React, { useState } from 'react';
import { View, TextInput, Button, Text } from 'react-native';
import { useNavigation } from '@react-navigation/native';

export default function RegisterStep1() {
  const navigation = useNavigation();
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');

  const handleNext = () => {
    if (password !== repeatPassword) return alert("Senhas não coincidem");
    navigation.navigate('RegisterStep2', { name, email, password });
  };

  return (
    <View>
      <Text>Nome completo</Text>
      <TextInput placeholder="Nome Completo" value={name} onChangeText={setName} />
      <Text>E-mail</Text>
      <TextInput placeholder="E-mail" value={email} onChangeText={setEmail} keyboardType="email-address" />
      <Text>Senha</Text>
      <TextInput placeholder="Senha" secureTextEntry value={password} onChangeText={setPassword} />
      <Text>Repita a senha</Text>
      <TextInput placeholder="Repetir senha" secureTextEntry value={repeatPassword} onChangeText={setRepeatPassword} />
      <Button title="Próxima Etapa" onPress={handleNext} />
    </View>
  );
}
