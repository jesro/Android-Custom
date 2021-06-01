   fun retrofitTrans() {

        val apiInterface = APIClient.getClient()?.create(ApiInterface::class.java)

        val model = apiInterface?.getModelTransResponse(convertPlainString(mid!!.text.toString())!!,
                convertPlainString(orderId!!.text.toString())!!,convertPlainString(txnAmounts!!.text.toString())!!)

        model?.enqueue(object : Callback<Any?> {
            override fun onFailure(call: Call<Any?>, t: Throwable) {
                call.cancel()
                txnToken!!.setText(t.message)
                txnTokenString = t.message
            }

            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                Log.d("okkk", response.toString())

            }
        })
    }

    fun convertPlainString(data: String?): RequestBody? {
        return data!!.toRequestBody()
    }

    fun paytmCode() {
        var host = "https://securegw-stage.paytm.in/"
        val callBackUrl = host + "theia/paytmCallback?ORDER_ID=" + orderIdString
        val paytmOrder = PaytmOrder(orderIdString, midString, txnTokenString, txnAmountString, callBackUrl)
        val transactionManager = TransactionManager(paytmOrder, object : PaytmPaymentTransactionCallback {
            override fun onTransactionResponse(bundle: Bundle) {
                Log.d("okkk", bundle.toString())
                val check = bundle.get("CHECKSUMHASH").toString()
                responseText?.text = bundle.toString()
            }

            override fun networkNotAvailable() {}
            override fun onErrorProceed(s: String) {
                Log.d("okkk", s)
            }

            override fun clientAuthenticationFailed(s: String) {
                Log.d("okkk", s)
            }

            override fun someUIErrorOccurred(s: String) {
                Log.d("okkk", s)
            }

            override fun onErrorLoadingWebPage(i: Int, s: String, s1: String) {
                Log.d("okkk", "$i $s $s1")
            }

            override fun onBackPressedCancelTransaction() {}
            override fun onTransactionCancel(s: String, bundle: Bundle) {
                Log.d("okkk", "$s $bundle")
            }
        })
        //transactionManager.setAppInvokeEnabled(false)
        transactionManager.setShowPaymentUrl(host + "theia/api/v1/showPaymentPage")
        transactionManager.startTransaction(this@MainActivity, ActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ActivityRequestCode && data != null) {
            Toast.makeText(this, data.getStringExtra("nativeSdkForMerchantMessage") + data.getStringExtra("response"), Toast.LENGTH_SHORT).show()
        }
    }
