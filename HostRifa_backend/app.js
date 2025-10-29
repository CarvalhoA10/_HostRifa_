const express = require('express')
const app = express()

const publicRouter = require('./routers/publicRouter')

app.use(express.json())

app.use('', publicRouter)

const PORT = 3000
app.listen(PORT, ()=>{
    console.log(`Servidor rodando em http://localhost:${PORT}`)
})