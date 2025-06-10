import {
  useFonts,
  Inter_400Regular,
  Inter_500Medium,
  Inter_600SemiBold,
  Inter_700Bold,
} from '@expo-google-fonts/inter';
import { RootNavigationContainer } from './src/navigation';
import { AuthProvider } from './src/contexts/auth/authContext';
import Toast from 'react-native-toast-message';
import { toastConfig } from './src/components/toastConfig';
import { View } from 'react-native';

export default function App() {

  const [fontsLoaded] = useFonts({
    Inter_400Regular,
    Inter_500Medium,
    Inter_600SemiBold,
    Inter_700Bold,
  });

  if (!fontsLoaded) {
    return null;
  }

  return (
    <View style={{ flex: 1 }}>
      <AuthProvider>
        <RootNavigationContainer />
        <Toast config={toastConfig}/>
      </AuthProvider>
    </View>
  );
}