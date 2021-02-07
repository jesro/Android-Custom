ComapiConfig config = new ComapiConfig() .apiSpaceId("aaa6274-g45455-dfdf4-dfdt1g43f-ht3434fgge3y4j5fgf") .authenticator(new Handler());
        Comapi.initialiseShared(getApplication(), config, new Callback<ComapiClient>() {
            @Override
            public void success(ComapiClient client) {
                //Use ComapiClient object to communicate with services
                compapiCommunicate(client);
            }

            @Override
            public void error(Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage() + " :Fail initializeComapi", Toast.LENGTH_LONG).show();
            }
        });
public class Handler extends ComapiAuthenticator {


    @Override
    public void onAuthenticationChallenge(AuthClient authClient, ChallengeOptions challengeOptions) {

        try {

            byte[] data;
            //<Shared secret> string must be the same as the value of the 'Shared secret' field in your push notification profile in Engagement Cloud.
            data = "abc44989458cccc498459895".getBytes("UTF-8");

            String base64Secret = Base64.encodeToString(data, Base64.DEFAULT);

            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");

            Map<String, Object> claims = new HashMap<>();
            claims.put("nonce", challengeOptions.getNonce());
            //<ID claim> string must be the same as the value of the 'ID claim' field in your push notification profile in Engagement Cloud.
            claims.put("sub", "sub");
            //<Audience> string must be the same as the value of the 'Audience' field in your push notification profile in Engagement Cloud.
            claims.put("aud", "https://api.comapi.com");
            //<Issuer> string must be the same as the value of the 'Issuer' field in your push notification profile in Engagement Cloud.
            claims.put("iss", "https://api.comapi.com/defaultauth");
            claims.put("iat", System.currentTimeMillis());
            claims.put("exp", System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));

            final String token = Jwts.builder()
                    .setHeader(header)
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, base64Secret)
                    .compact();

            //Pass the JWT token to the SDK.
            authClient.authenticateWithToken(token);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //Authorisation failed.
            authClient.authenticateWithToken(null);
        }
    }
}

private void compapiCommunicate(final ComapiClient client)
    {
        if(client.getSession() != null && client.getSession().isSuccessfullyCreated()) {
            client.service().profile().getProfile(client.getSession().getProfileId(), new Callback<ComapiResult<Map<String, Object>>>() {
                @Override
                public void success(ComapiResult<Map<String, Object>> result) {
                    //Toast.makeText(MainActivity.this, result+ " :Success AccountCreation", Toast.LENGTH_LONG).show();
                    Map<String, Object> additionalMap = new HashMap<>();
                    //Add the user's email address to the profile
                    additionalMap.put("email", "abc@gmail.com");
                    client.service().profile().patchMyProfile(additionalMap, result.getETag(), new Callback<ComapiResult<Map<String, Object>>>() {
                        @Override
                        public void success(ComapiResult<Map<String, Object>> result) {
                            //Toast.makeText(MainActivity.this, result+" :Success PatchProfile", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void error(Throwable t) {
                            //Toast.makeText(MainActivity.this, t.getMessage()+" :Fail PatchProfile", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void error(Throwable t) {
                    //Toast.makeText(MainActivity.this, t.getMessage() +" :Fail AccountCreation", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            client.service().session().startSession(new Callback<Session>() {
                @Override
                public void success(Session result) {
                    //Toast.makeText(MainActivity.this, result+" :Success startSession", Toast.LENGTH_LONG).show();
                    compapiCommunicate(client);
                }

                @Override
                public void error(Throwable t) {
                    //Toast.makeText(MainActivity.this, t.getMessage() + " :Fail startSession", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
