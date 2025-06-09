import {
    View,
    Text,
    StyleSheet,
    Image,
    TextInput,
    TouchableOpacity,
    KeyboardAvoidingView,
    ScrollView,
    Platform,
    Alert
} from "react-native";
import { useState } from "react";
import colors from "../../../globals/theme/colors";
import Button from "../../../components/Button";
import { Feather } from '@expo/vector-icons';
import { api } from "../../../infra/apis/api";
import { useAuth } from '../../../hooks/authHook';
import { AuthStore } from '../../../infra/stores/AuthStore';
import { Container } from "../../../components/Container";

export function Login() {
    const { setToken } = useAuth();

    const [isLogging, setIsLogging] = useState(false);

    const [passwordVisible, setPasswordVisible] = useState(false);
    const [email, setEmail] = useState('admin@admin.com');
    const [password, setPassword] = useState('admin');

    const authStore = new AuthStore();

    const handleLogin = async () => {
        try {
            setIsLogging(true)
            if (!email || !password) {
                Alert.alert('Erro', 'Preencha todos os campos');
                return;
            }

            const response = await api.post('/auth/login', { email, password });
            await authStore.set(response.data.token);
            setToken(response.data.token);
        } catch (error) {
            Alert.alert('Erro', 'Email ou senha inválidos');
            console.log(error)
        } finally {
            setIsLogging(false)
        }
    }

    return (
        <Container style={styles.container}>
            <KeyboardAvoidingView
                behavior={Platform.OS === "ios" ? "padding" : "height"}
                style={styles.keyboardAvoidingView}
                keyboardVerticalOffset={Platform.OS === "ios" ? 60 : 0}
            >
                <ScrollView
                    contentContainerStyle={styles.scrollContainer}
                    keyboardShouldPersistTaps="handled"
                    showsVerticalScrollIndicator={false}
                >
                    <View style={styles.logoContainer}>
                        <Image
                            source={require("../../../../assets/Logo.png")}
                            style={styles.logo}
                            resizeMode="contain"
                        />
                        <Text style={styles.appName}>RecomendaCine</Text>
                    </View>

                    {/* Campo de E-mail */}
                    <View style={styles.inputGroup}>
                        <Text style={styles.label}>E-mail</Text>
                        <View style={styles.inputContainer}>
                            <Feather name="user" size={18} color={colors.gray} style={styles.icon} />
                            <TextInput
                                value={email}
                                onChangeText={setEmail}
                                placeholder="Digite seu e-mail"
                                placeholderTextColor={colors.gray}
                                style={styles.input}
                                autoCapitalize="none"
                                autoCorrect={false}
                            />
                        </View>
                    </View>

                    {/* Campo de Senha */}
                    <View style={styles.inputGroup}>
                        <Text style={styles.label}>Senha</Text>
                        <View style={styles.inputContainer}>
                            <Feather name="lock" size={18} color={colors.gray} style={styles.icon} />
                            <TextInput
                                value={password}
                                onChangeText={setPassword}
                                placeholder="Digite sua senha"
                                placeholderTextColor={colors.gray}
                                secureTextEntry={!passwordVisible}
                                style={styles.input}
                                autoCapitalize="none"
                            />
                            <TouchableOpacity
                                onPress={() => setPasswordVisible(!passwordVisible)}
                                style={styles.eyeIcon}
                            >
                                <Feather
                                    name={passwordVisible ? "eye-off" : "eye"}
                                    size={18}
                                    color={colors.gray}
                                />
                            </TouchableOpacity>
                        </View>
                    </View>

                    <Button
                        title="Entrar"
                        variant="outlined"
                        style={styles.loginButton}
                        isLoading={isLogging}
                        onPress={handleLogin}
                    />

                    <TouchableOpacity style={styles.noAccountButton}>
                        <Text style={styles.noAccountText}>Não tenho uma conta</Text>
                    </TouchableOpacity>
                </ScrollView>
            </KeyboardAvoidingView>
        </Container>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: colors.background,
    },
    keyboardAvoidingView: {
        flex: 1,
    },
    scrollContainer: {
        paddingHorizontal: 20,
        paddingBottom: 20,
        flexGrow: 1,
        justifyContent: 'center',
    }, 
    logoContainer: {
        alignItems: 'center',
        marginBottom: 20,
    },
    logo: {
        width: 150,
        height: 150
    },
    appName: {
        fontFamily: "Inter_700Bold",
        fontSize: 24,
        color: colors.primary,
        marginTop: 10, 
        marginBottom: 20
    },
    title: {
        fontSize: 20,
        fontFamily: "Inter_700Bold",
        color: colors.primary,
        textAlign: 'center',
        marginBottom: 40,
    },
    bold: {
        fontFamily: "Inter_700Bold",
    },
    inputContainer: {
        flexDirection: "row",
        alignItems: "center",
        backgroundColor: colors.inputBackground,
        borderColor: colors.border,
        borderWidth: 1,
        borderRadius: 12,
        paddingHorizontal: 12,
        marginBottom: 8,
        height: 48,
    },
    input: {
        flex: 1,
        fontFamily: "Inter_500Medium",
        color: colors.text,
        fontSize: 14,
        paddingVertical: 0,
    },
    icon: {
        marginRight: 8,
    },
    eyeIcon: {
        padding: 8,
    },
    loginButton: {
        marginTop: 20,
        width: "100%",
    },
    noAccountButton: {
        marginTop: 24,
        alignSelf: 'center',
    },
    noAccountText: {
        color: colors.text,
        fontSize: 14,
        fontFamily: "Cairo_600SemiBold",
        opacity: 0.6,
    },    inputGroup: {
        marginBottom: 16,
    },
    label: {
        fontFamily: "Inter_600SemiBold",
        fontSize: 14,
        color: colors.text,
        marginBottom: 8,
        marginLeft: 4,
    },
});
