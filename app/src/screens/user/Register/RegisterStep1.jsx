// screens/RegisterStep1.js
import { useState } from 'react';
import { View, TextInput, Button, Text } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import Toast from 'react-native-toast-message';

export default function RegisterStep1() {
  const navigation = useNavigation();
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');

  const isValidEmail = (email) => {
    return email.includes('@') && email.includes('.');
  };

  const isValidPassword = (password) => {
    const minLength = password.length >= 6;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    return minLength && hasUpperCase && hasLowerCase;
  };

  const handleNext = () => {
    if (!name || !email || !password || !repeatPassword) {
      Toast.show({
        type: 'error',
        text1: 'Campos obrigatórios',
        text2: 'Preencha todos os campos antes de continuar.',
      });
      return;
    }

    if (!isValidEmail(email)) {
      Toast.show({
        type: 'error',
        text1: 'E-mail inválido',
        text2: 'Insira um e-mail válido (ex: exemplo@dominio.com).',
      });
      return;
    }

    if (!isValidPassword(password)) {
      Toast.show({
        type: 'error',
        text1: 'Senha inválida',
        text2: 'A senha deve ter ao menos 6 caracteres, 1 letra minúscula e 1 maiúscula.',
      });
      return;
    }

    if (password !== repeatPassword) {
      Toast.show({
        type: 'error',
        text1: 'Senhas não coincidem',
        text2: 'A senha e a confirmação devem ser iguais.',
      });
      return;
    }

    navigation.navigate('RegisterStep2', { name, email, password });
  };

  return (
    <View style={{ padding: 20 }}>
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
