// create an axios instance
import axios from 'axios';
import { authInterceptor } from './inteceptors/authInterceptor';

export const api = axios.create({
  baseURL: "http://10.0.2.2:8080",
});

api.interceptors.request.use(authInterceptor);