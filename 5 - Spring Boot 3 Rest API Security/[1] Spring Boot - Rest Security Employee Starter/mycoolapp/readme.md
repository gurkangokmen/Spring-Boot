# POST, PUT, DELETE error despite correct credentials

You got an error because CSRF handling is 'on' by default with Spring Security.

You can disabled it by adding http.csrf().disable();