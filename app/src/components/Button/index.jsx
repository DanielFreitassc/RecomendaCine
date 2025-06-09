import React from 'react';
import {
  TouchableOpacity,
  Text,
  StyleSheet,
  ActivityIndicator,
  View
} from 'react-native';
import colors from '../../globals/theme/colors';

const Button = ({
  title,
  onPress,
  style,
  textStyle,
  variant = 'filled',
  isLoading = false,
  disabled = false
}) => {
  const isOutlined = variant === 'outlined';
  const isDisabled = disabled || isLoading;

  return (
    <TouchableOpacity
      style={[
        styles.base,
        isOutlined ? styles.outlined : styles.filled,
        isDisabled && styles.disabled,
        style,
      ]}
      onPress={onPress}
      activeOpacity={0.8}
      disabled={isDisabled}
    >
      <View style={styles.contentContainer}>
        {isLoading ? (
          <ActivityIndicator
            size="small"
            color={isOutlined ? colors.primary : colors.white}
            style={styles.loadingIndicator}
          />
        ) : (
          <Text
            style={[
              styles.text,
              isOutlined ? styles.textOutlined : styles.textFilled,
              isDisabled && styles.textDisabled,
              textStyle,
            ]}
            numberOfLines={1}
            ellipsizeMode="tail"
          >
            {title}
          </Text>
        )}
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  base: {
    height: 46,
    paddingHorizontal: 32,
    borderRadius: 24,
    justifyContent: 'center',
    alignItems: 'center',
    alignSelf: 'center',
    marginTop: 16,
  },
  filled: {
    backgroundColor: colors.primary,
  },
  outlined: {
    backgroundColor: 'transparent',
    borderWidth: 1,
    borderColor: colors.primary,
  },
  disabled: {
    opacity: 0.6,
  },
  contentContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 8,
  },
  text: {
    fontSize: 16,
    fontFamily: 'Cairo_700Bold',
    textAlign: 'center',
  },
  textFilled: {
    color: colors.white,
  },
  textOutlined: {
    color: colors.primary,
  },
  textDisabled: {
    opacity: 0.8,
  },
  loadingIndicator: {
    marginRight: 6,
  },
});

export default Button;
