const userService = {
    create: async (user) => await fetch('http://localhost:8080/admin/users',
                                        {
                                                "method": "POST",
                                                "headers": {
                                                    "Content-Type": "application/json"
                                                },
                                                "body": JSON.stringify(user)
                                        }),

    readAuth: async () => await fetch("http://localhost:8080/user",
                                      {
                                            "method": "GET",
                                            "headers": {"Accept": "application/json"},
                                      }),

    readAll: async () => await fetch("http://localhost:8080/admin/users",
                                     {
                                        "method": "GET",
                                        "headers": {"Accept": "application/json"},
                                    }),

    readById: async (id) => await fetch(`http://localhost:8080/admin/users/${id}`,
                                        {
                                                "method": "GET",
                                                "headers": {"Accept": "application/json"},
                                        }),

    update: async (user) => await fetch('http://localhost:8080/admin/users/${id}',
                                        {
                                                "method": "PUT",
                                                "headers": {"Content-Type": "application/json"},
                                                "body": JSON.stringify(user)
                                        }),

    delete: async (id) => await fetch(`http://localhost:8080/admin/users/${id}`,
                                      {
                                            method: "DELETE"
                                      })
};