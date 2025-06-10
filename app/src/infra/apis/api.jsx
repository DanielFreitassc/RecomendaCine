// infra/apis/api.js
import axios from 'axios';
import { authInterceptor } from './inteceptors/authInterceptor';
import Toast from 'react-native-toast-message';

export const api = axios.create({
  baseURL: "http://10.0.2.2:8080",
});

// Interceptador de requisições para adicionar o token
api.interceptors.request.use(authInterceptor);

// Interceptador de respostas para capturar erros
api.interceptors.response.use(
  response => response, // passa respostas com sucesso direto
  error => {
    // Tenta extrair a mensagem de erro da resposta
    const message = error?.response?.data?.message || 'Erro inesperado';

    // Exibe um toast com a mensagem de erro
    Toast.show({
      type: 'error',
      text1: 'Erro',
      text2: message,
    });

    return Promise.reject(error); // ainda propaga o erro para tratamento local, se necessário
  }
);
