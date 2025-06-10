// screens/RegisterStep3.js
import React, { useEffect, useState } from 'react';
import { View, Text, Button, TouchableOpacity, ScrollView } from 'react-native';
import { useNavigation, useRoute } from '@react-navigation/native';
import { api } from '../../../infra/apis/api';
import genresData from '../../../../data/genres.json';

export default function RegisterStep3() {
  const navigation = useNavigation();
  const route = useRoute();
  const [genres, setGenres] = useState([]);
  const [selectedGenres, setSelectedGenres] = useState([]);

  useEffect(() => {
    fetchGenres();
  }, []);

  const fetchGenres = () => {
    setGenres(genresData.genres);
  };

  const toggleGenre = (genre) => {
    setSelectedGenres(prev =>
      prev.includes(genre) ? prev.filter(g => g !== genre) : [...prev, genre]
    );
  };

  const handleSubmit = async () => {
    try {
      const payload = {
        name: route.params.name,
        email: route.params.email,
        password: route.params.password,
        favoriteMediaType: route.params.favoriteMediaType,
        favoriteGenre: selectedGenres,
        image: "foto"
      };

      await api.post('/users', payload);
      alert("Cadastro realizado!");
      navigation.navigate('Login'); 
    } catch (err) {
      console.error(err);
      alert("Erro ao cadastrar");
    }
  };

  return (
    <ScrollView>
      <Text>Escolha seus gêneros favoritos</Text>
      {genres.map(genre => (
        <TouchableOpacity key={genre} onPress={() => toggleGenre(genre)}>
          <Text style={{ backgroundColor: selectedGenres.includes(genre) ? 'orange' : 'gray' }}>{genre}</Text>
        </TouchableOpacity>
      ))}
      <Button title="Confirmar" onPress={handleSubmit} />
    </ScrollView>
  );
}
