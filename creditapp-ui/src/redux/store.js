import { configureStore } from '@reduxjs/toolkit';
import customerReducer from './customerSlice';  // customerSlice'ı içeri aktarıyoruz

// Store oluşturuluyor
export const store = configureStore({
  reducer: {
    customer: customerReducer,  // customerReducer'ı store'a dahil ediyoruz
  },
});