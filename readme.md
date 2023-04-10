## The plan

- code generation
    - should work in the json/sql files, in the db console (maybe already work, need to check)
- readme.me generation
- providing the whole project as a context for code explanation actions
- checking the code, suggesting improvements, checking for bugs
- refactoring existing code (with a dialog?)
- progress bar (request to openai)
- continuous text/code generation (???)
- errors/warnings?
- rf: a lot of possible cast/npe exceptions, need to fix
- consider number of tokens in the openai request body
- maybe current gpt questions are too verbose?
- clean comments chars?
- hotkeys
- use additional threads for http calls?
- validate response and retry if needed
- if something goes wrong with the psi manipulation, just recreate the document from scratch
- refactor the class structure