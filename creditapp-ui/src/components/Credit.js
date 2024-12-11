import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';

function Credit () {
  // State'ler
  const customerNumber = useSelector((state) => state.customer.customerNumber);
  const customerName = useSelector((state) => state.customer.customerName);
  const customerSurName = useSelector((state) => state.customer.customerSurName);
  const [customerNo] = useState(customerNumber);
  const [creditType, setCreditType] = useState('');
  const [creditAmount, setCreditAmount] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);



  // Kredi tipini seçmek için bir fonksiyon
  const handleCreditTypeChange = (e) => {
    setCreditType(e.target.value);
  };

  // Kredi miktarını girmek için bir fonksiyon
  const handleCreditAmountChange = (e) => {
    setCreditAmount(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    // Kredi miktarı ve tipinin geçerli olduğundan emin olalım
    if (!creditType || !creditAmount || isNaN(creditAmount) || creditAmount <= 0) {
      setError('Lütfen geçerli bir kredi tipi ve miktarı girin.');
      return;
    }
  
    // Kredi bilgilerini Customer nesnesine atama
    const customer = {
      customerNo,
      creditType,
      creditAmount: parseFloat(creditAmount),
    };
  
    try {
      setLoading(true);
      setError(null);
      setSuccess(null);
  
      // API'ye POST isteği gönderme
      const response = await axios.post('/bff/applications', customer, {
        headers: {
          'Content-Type': 'application/json', // JSON formatında veri gönderiyoruz
        },
      });
  
      // API'nin cevabını kontrol et
      if (response.status === 200) {
        setSuccess(" - " + response.data.applicationNo + " numaralı kredi başvurunuz alındı. Sonuç mail adresinize iletilecektir." );
        // API cevabını konsola yazdırma
      } else {
        throw new Error('API isteği başarısız oldu');
      }
    } catch (error) {     
      if (error.response) {
        if (error.status>=400 && error.status<500){
          setError(" - " + error.status + " - " + error.response.data );
        }else if (error.status>=500){
          setError(" - "  + error.status + " - " + ' Ağ hatası ');
        } else {
        // Diğer hata durumları
        setError('Bir hata oluştu.');
      }
    
      }
    
    
    } finally {
      setLoading(false);
    }
  };



  return (


    <div>
      <h1>Kredi Başvurusu</h1>
      <form onSubmit={handleSubmit}>

      <div>
          <label><b>Müşteri No: </b>{customerNumber}</label>
        </div>

        <div>
          <label><b>Müşteri Adı: </b>{customerName}</label>
        </div>

        <div>
          <label><b>Müşteri Soyadı: </b>{customerSurName}</label>
        </div>
       
        
        <div>
          <label htmlFor="creditType"><b>Kredi Tipi: </b></label>
          <select id="creditType" value={creditType} onChange={handleCreditTypeChange}>
            <option value="">Kredi Tipini Seçin</option>
            <option value="1">İhtiyaç Kredisi</option>
            <option value="2">Konut Kredisi</option>
            <option value="3">Araç Kredisi</option>
          </select>
        </div>

        <div>
          <label htmlFor="creditAmount"><b>Kredi Miktarı: </b></label>
          <input
            type="number"
            id="creditAmount"
            value={creditAmount}
            onChange={handleCreditAmountChange}
            min="1"
            step="any"
            placeholder="Kredi miktarını girin"
          />
        </div>


        {error && <p style={{ color: 'red' }}>{error}</p>}
        {success && <p style={{ color: 'green' }}>{success}</p>}

        <button type="submit" disabled={loading}>
          {loading ? 'Gönderiliyor...' : 'Başvuruyu Gönder'}
        </button>
      </form>
    </div>
  );
};

export default Credit;