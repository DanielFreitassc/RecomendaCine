import { BaseToast, ErrorToast } from 'react-native-toast-message';

export const toastConfig = {
  success: (props) => (
    <BaseToast
      {...props}
      style={{ 
        borderLeftColor: 'green',
        position: 'absolute',
        top: 50,
        left: 20,
        right: 20,
        zIndex: 9999, // Adiciona zIndex alto para garantir que fique acima de outros elementos
      }}
      contentContainerStyle={{
        paddingHorizontal: 15
      }}
      text1Style={{
        fontSize: 16,
        fontWeight: 'bold',
      }}
      text2Style={{
        fontSize: 14,
        flexWrap: 'wrap',
      }}
    />
  ),
  error: (props) => (
    <ErrorToast
      {...props}
      style={{
        position: 'absolute',
        top: 50,
        left: 20,
        right: 20,
        zIndex: 9999, // Adiciona zIndex alto para garantir que fique acima de outros elementos
      }}
      contentContainerStyle={{
        paddingHorizontal: 15
      }}
      text1Style={{
        fontSize: 16,
        fontWeight: 'bold',
      }}
      text2Style={{
        fontSize: 14,
        flexWrap: 'wrap',
      }}
    />
  ),
};