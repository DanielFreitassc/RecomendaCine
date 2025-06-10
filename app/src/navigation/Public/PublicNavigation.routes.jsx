import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Login } from '../../screens/Common/Login';
import RegisterStep1  from '../../screens/user/Register/RegisterStep1';
import RegisterStep2 from '../../screens/user/Register/RegisterStep2';
import RegisterStep3 from '../../screens/user/Register/RegisterStep3';

export function PublicNavigationRoutes() {
    const Stack = createNativeStackNavigator();

    return (
        <Stack.Navigator initialRouteName='Login' screenOptions={{ headerShown: false }}>
            <Stack.Screen name="Login" component={Login} />
            <Stack.Screen name="RegisterStep1" component={RegisterStep1} />
            <Stack.Screen name="RegisterStep2" component={RegisterStep2} />
            <Stack.Screen name="RegisterStep3" component={RegisterStep3} />
        </Stack.Navigator>
    );
}
