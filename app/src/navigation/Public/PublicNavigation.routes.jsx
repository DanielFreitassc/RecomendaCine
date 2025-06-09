import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Login } from '../../screens/Common/Login';

export function PublicNavigationRoutes() {
    const Stack = createNativeStackNavigator();

    return (
        <Stack.Navigator initialRouteName='Login'
            screenOptions={{ headerShown: false }}>
            <Stack.Screen name="Login" component={Login} />
        </Stack.Navigator>
    )
}