import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import { useDispatch, useSelector } from 'react-redux';
import { setCustomerNumber, setCustomerName, setCustomerSurName } from '../redux/customerSlice';

function Customer() {

  const dispatch = useDispatch();
  const customerNumber = useSelector((state) => state.customer.customerNumber);
  const customerName = useSelector((state) => state.customer.customerName);
  const customerSurName = useSelector((state) => state.customer.customerSurName);
  const handleSetCustomerNumber = (number) => {
    dispatch(setCustomerNumber(number));  // customerNumber'ı güncelliyoruz
  };
  const handleSetCustomerName = (newCustomerName) => {
    dispatch(setCustomerName(newCustomerName));  // customerName'i güncelliyoruz
  };
  const handleSetCustomerSurName = (newCustomerSurName) => {
    dispatch(setCustomerSurName(newCustomerSurName));  // customerSurName'i güncelliyoruz
  };


  // Input müşteri numarasını tutacak state değişkeni
  const [input, setInput] = useState('');

  // getById ile Bff den gelecek müşteri data verisini tutacak state değişkeni 
  const [customerData, setCustomerData] = useState(null);

  // Hata mesajını ve işlem durumunu tutacak state
  const [errorMessage, setErrorMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);

  // Input değişkenini güncelleyen fonksiyon
  const customerNoInputChange = (event) => {
    const value = event.target.value;
    if (value.length <= 8 && !isNaN(value)) {
      setInput(value);
    }
  };





  // get customer API sini çağıran fonksiyon


  const getCustomerButtonClick = async () => {
      setErrorMessage('');  // Her butona basıldığında önceki hata mesajını sıfırlıyoruz
      setIsSuccess(false);  // Başarı durumunu sıfırlıyoruz
  
      if (input.length === 8) {
        try {
          const response = await axios.get(`/bff/customers/${input}`);
          
          // Başarılı yanıt durumunda işlemi yapıyoruz
          setCustomerData(response.data); // Gelen veriyi state'e kaydediyoruz
          setIsSuccess(true);  // İşlem başarılı olduğunda success durumunu true yapıyoruz
          handleSetCustomerNumber(response.data.customerNo);
          handleSetCustomerName(response.data.name);
          handleSetCustomerSurName(response.data.surname);
          
        } catch (error) {
          if (error.response) {
            if (error.status>=400 && error.status<500){
              setErrorMessage(" - " +error.status + " - " + error.response.data );
            }if (error.status>=500){
              setErrorMessage(" - "  + error.status + " - " + ' Ağ hatası ');
            }
           
          } else {
            // Diğer hata durumları
            setErrorMessage('Bir hata oluştu.');
          }
  
          // Veriyi sıfırlıyoruz
          setCustomerData(null); 
          handleSetCustomerNumber(0);
          handleSetCustomerName("geçersiz");
          handleSetCustomerSurName("geçersiz");
        }
      } else {
        setErrorMessage('Lütfen geçerli bir 8 haneli sayı girin!');
      }
  };

  return (
    <div>
      <h2>Müşteri Numarasını Girin</h2>
      <input
        type="number"
        value={input}
        onChange={customerNoInputChange}
        placeholder="8 haneli sayı"
      />
      
      <button onClick={getCustomerButtonClick}>GETİR</button>

      {/* Hata durumunda mesaj göster */}
      {errorMessage && <p style={{ color: 'red' }}>{errorMessage }</p>  }

      {/* API'den gelen veriyi ekrana yazdırıyoruz */}
      {customerData && !errorMessage && (
        <div>
          <h2>Müşteri Bilgileri:</h2>
          <ul>
            <li><strong>Müşteri No:</strong> {customerData.customerNo}</li>
            <li><strong>İsim:</strong> {customerData.name}</li>
            <li><strong>Soyisim:</strong> {customerData.surname}</li>
            <li><strong>TC No:</strong> {customerData.tcNo}</li>
            <li><strong>Email:</strong> {customerData.mail}</li>
            
          </ul>
        </div>
      )}

      {/* İşlem başarılıysa başka bir buton ekleyelim */}
      
      {isSuccess && (

        <Link to="/credit">
        <button> Kredi başvuru sayfasına git  </button>
      </Link>

      )}
    </div>
  );
}

export default Customer;