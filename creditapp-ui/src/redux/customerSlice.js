import { createSlice } from '@reduxjs/toolkit';

// 1. Initial state: customerNumber ve customerName'ı başlatıyoruz
const initialState = {
  customerNumber: 0,
  customerName: 'geçersiz', // Başlangıçta customerName boş olacak
  customerSurName: 'geçersiz', // Başlangıçta customerSurName boş olacak
};

// 2. createSlice ile bir slice oluşturuyoruz
const customerSlice = createSlice({
  name: 'customer',  // Slice adı
  initialState,  // Başlangıç durumu
  reducers: {
    setCustomerNumber: (state, action) => {
      state.customerNumber = action.payload;  // customerNumber'ı güncelliyoruz
    },
    setCustomerName: (state, action) => {
      state.customerName = action.payload;  // customerName'i güncelliyoruz
    },
    setCustomerSurName: (state, action) => {
      state.customerSurName = action.payload;  // customerSurName'i güncelliyoruz
    },
  },
});

// 3. Action'ları dışarıya export ediyoruz
export const { setCustomerNumber, setCustomerName , setCustomerSurName} = customerSlice.actions;

// 4. Reducer'ı dışarıya export ediyoruz
export default customerSlice.reducer;