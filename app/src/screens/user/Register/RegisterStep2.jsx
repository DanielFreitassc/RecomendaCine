// screens/RegisterStep2.js
import React, { useState } from 'react';
import { View, Text, Button, TouchableOpacity } from 'react-native';
import { useNavigation, useRoute } from '@react-navigation/native';

const mediaOptions = [
  { label: 'Filmes', value: 'MOVIE' },
  { label: 'Séries', value: 'SERIES' },
  { label: 'Animações', value: 'ANIMATION' },
  { label: 'Doramas', value: 'DORAMA' },
  { label: 'Animes', value: 'ANIME' }
];

export default function RegisterStep2() {
  const navigation = useNavigation();
  const route = useRoute();
  const [selectedMedia, setSelectedMedia] = useState(null);

  const handleNext = () => {
    if (!selectedMedia) return alert("Selecione um tipo");
    navigation.navigate('RegisterStep3', {
      ...route.params,
      favoriteMediaType: selectedMedia
    });
  };

  return (
    <View>
      <Text>O que deseja que seja recomendado?</Text>
      {mediaOptions.map(opt => (
        <TouchableOpacity key={opt.value} onPress={() => setSelectedMedia(opt.value)}>
          <Text style={{ backgroundColor: selectedMedia === opt.value ? 'orange' : 'gray' }}>{opt.label}</Text>
        </TouchableOpacity>
      ))}
      <Button title="Próximo" onPress={handleNext} />
    </View>
  );
}
